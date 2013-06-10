package jmx.threads;

import java.lang.management.ThreadInfo;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ThreadInfoMap {
	
	Map<Long, DetailedThreadInfo> map = new HashMap<>();
	
	public void update(ThreadInfo info){
		if(info == null){
			System.err.println("Info is null");
			System.exit(0);
		}
		DetailedThreadInfo dInfo = map.get(info.getThreadId());
		if(dInfo == null){
			map.put(info.getThreadId(), new DetailedThreadInfo(info));
		}else{
			dInfo.update(info);
		}
		
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(DetailedThreadInfo info : map.values()){
			builder.append(info);
		}
		return builder.toString();
	}
	
	private class DetailedThreadInfo{
		
		private long blockedTime;
		private long blockedCount;
		private final long id;
		private final String name;
		private Map<String, Set<String>> blockedData = new HashMap<>();
		public DetailedThreadInfo(ThreadInfo info){
			this.id = info.getThreadId();
			this.name = info.getThreadName();
			update(info);
		}
		private void update(ThreadInfo info) {
			blockedTime = info.getBlockedTime();
			blockedCount = info.getBlockedCount();
			String lockName = info.getLockName();
			if(lockName != null){
				Set<String> lockOwners = blockedData.get(lockName);
				if(lockOwners == null){
					lockOwners = new LinkedHashSet<>();
				}
				lockOwners.add(info.getLockOwnerName());
				blockedData.put(lockName, lockOwners);
			}
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("------------------------------ "+name+"@"+id+"------------------------------------------");
			builder.append("\nTotal Blocked Time : "+blockedTime);
			builder.append("\nTotal times blocked :"+blockedCount);
			for(String lock: blockedData.keySet()){
				builder.append("\n"+lock+":"+blockedData.get(lock));
			}
			builder.append("\n----------------------------------------------------------------------------------------\n");
			return builder.toString();
		}
	}

}
