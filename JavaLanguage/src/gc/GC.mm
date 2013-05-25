<map version="0.9.0">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node COLOR="#000000" CREATED="1368579670951" ID="ID_882237040" MODIFIED="1368582340360" TEXT="GC">
<font NAME="SansSerif" SIZE="20"/>
<hook NAME="accessories/plugins/HierarchicalIcons.properties"/>
<hook NAME="accessories/plugins/AutomaticLayout.properties"/>
<node COLOR="#0033ff" CREATED="1368579727578" HGAP="188" ID="ID_900877875" MODIFIED="1368581159129" POSITION="right" TEXT="Eliminating fragmentation is called Compaction" VSHIFT="-13">
<edge STYLE="sharp_bezier" WIDTH="8"/>
<font NAME="SansSerif" SIZE="18"/>
</node>
<node COLOR="#0033ff" CREATED="1368579979933" HGAP="103" ID="ID_636677492" MODIFIED="1368581541838" POSITION="left" STYLE="fork" TEXT="Design Choices" VSHIFT="265">
<edge STYLE="sharp_bezier" WIDTH="8"/>
<font NAME="SansSerif" SIZE="18"/>
<node COLOR="#00b439" CREATED="1368579992100" ID="ID_1915125938" MODIFIED="1368581159130" TEXT="Serial v/s Parallel">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="16"/>
</node>
<node COLOR="#00b439" CREATED="1368580002278" ID="ID_1845707418" MODIFIED="1368581159131" TEXT="Cocurrent v/s Stop the World">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="16"/>
</node>
<node COLOR="#00b439" CREATED="1368580013765" ID="ID_1210658027" MODIFIED="1368581159131" TEXT="Compacting v/s Non-Compacting v/s Copy">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="16"/>
</node>
</node>
<node COLOR="#0033ff" CREATED="1368580082382" HGAP="211" ID="ID_415196278" MODIFIED="1368581195081" POSITION="right" TEXT="PerformanceMetrics" VSHIFT="14">
<edge STYLE="sharp_bezier" WIDTH="8"/>
<font NAME="SansSerif" SIZE="18"/>
<node COLOR="#00b439" CREATED="1368580100810" ID="ID_1181180926" MODIFIED="1368581159132" TEXT="Throughput">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="16"/>
</node>
<node COLOR="#00b439" CREATED="1368580110036" ID="ID_1639197349" MODIFIED="1368581159133" TEXT="GC OverHead">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="16"/>
</node>
<node COLOR="#00b439" CREATED="1368580116362" ID="ID_522530892" MODIFIED="1368581159134" TEXT="Pause Time">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="16"/>
</node>
<node COLOR="#00b439" CREATED="1368580124827" ID="ID_371399918" MODIFIED="1368581159134" TEXT="Frequency">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="16"/>
</node>
<node COLOR="#00b439" CREATED="1368580133979" ID="ID_1580283214" MODIFIED="1368581159135" TEXT="FootPrint">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="16"/>
</node>
<node COLOR="#00b439" CREATED="1368580140170" ID="ID_1655798157" MODIFIED="1368581159135" TEXT="Promptness">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="16"/>
</node>
</node>
<node COLOR="#0033ff" CREATED="1368580912091" HGAP="56" ID="ID_162274292" MODIFIED="1368582271393" POSITION="left" TEXT="Heap Partition" VSHIFT="-29">
<edge STYLE="sharp_bezier" WIDTH="8"/>
<font NAME="SansSerif" SIZE="18"/>
<node COLOR="#00b439" CREATED="1368580924984" HGAP="51" ID="ID_200622139" MODIFIED="1368582325101" TEXT="Young Generation" VSHIFT="48">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="16"/>
<node COLOR="#990000" CREATED="1368580935598" ID="ID_950329664" MODIFIED="1368581159136" TEXT="Eden Space">
<font NAME="SansSerif" SIZE="14"/>
</node>
<node COLOR="#990000" CREATED="1368580940875" ID="ID_454107114" MODIFIED="1368581159136" TEXT="Survivor Space (From)">
<font NAME="SansSerif" SIZE="14"/>
</node>
<node COLOR="#990000" CREATED="1368580950714" ID="ID_96661801" MODIFIED="1368581159137" TEXT="Survivor Space(To)">
<font NAME="SansSerif" SIZE="14"/>
</node>
</node>
<node COLOR="#00b439" CREATED="1368582277375" HGAP="63" ID="ID_1965237213" MODIFIED="1368582327502" TEXT="Old Generation" VSHIFT="-30">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="16"/>
</node>
<node COLOR="#00b439" CREATED="1368582283800" HGAP="56" ID="ID_1800247221" MODIFIED="1368582311463" TEXT="Permanent Generation" VSHIFT="-93">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="16"/>
</node>
</node>
<node COLOR="#0033ff" CREATED="1368581116799" HGAP="243" ID="ID_208915969" MODIFIED="1368581201865" POSITION="right" TEXT="GCTypes" VSHIFT="-163">
<edge STYLE="sharp_bezier" WIDTH="8"/>
<font NAME="SansSerif" SIZE="18"/>
<node COLOR="#00b439" CREATED="1368581206901" ID="ID_759400142" MODIFIED="1368581217253" TEXT="Minor and Major">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="16"/>
</node>
</node>
<node COLOR="#0033ff" CREATED="1368581327691" ID="ID_1534817811" MODIFIED="1368581497831" POSITION="left" TEXT="Thread Local Allocation Buffers (TLAB)">
<edge STYLE="sharp_bezier" WIDTH="8"/>
<font NAME="SansSerif" SIZE="18"/>
<node COLOR="#00b439" CREATED="1368581344231" ID="ID_247939990" MODIFIED="1368581391976" TEXT="Thread Specific Buffers - Hence no synchronization needed">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="16"/>
</node>
</node>
<node COLOR="#0033ff" CREATED="1368582344443" ID="ID_813067746" MODIFIED="1368582873107" POSITION="right" TEXT="Collectors">
<edge STYLE="sharp_bezier" WIDTH="8"/>
<font NAME="SansSerif" SIZE="18"/>
<node COLOR="#00b439" CREATED="1368582371991" ID="ID_168677609" MODIFIED="1368582376836" TEXT="SerialCollector">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="16"/>
</node>
<node COLOR="#00b439" CREATED="1368582377326" ID="ID_1023283958" MODIFIED="1368582382680" TEXT="ParallelCollector">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="16"/>
<node COLOR="#990000" CREATED="1368583326844" ID="ID_474580651" MODIFIED="1368583343238" TEXT="PauseTimeGoal - -XX:MaxGCPauseMillis=n">
<font NAME="SansSerif" SIZE="14"/>
</node>
<node COLOR="#990000" CREATED="1368583346947" ID="ID_632376003" MODIFIED="1368583366417" TEXT="Throughput Goal - -XX:GCTimeRatio=n">
<font NAME="SansSerif" SIZE="14"/>
</node>
<node COLOR="#990000" CREATED="1368583366634" ID="ID_1759215563" MODIFIED="1368583371129" TEXT="FootPrintGoal">
<font NAME="SansSerif" SIZE="14"/>
</node>
</node>
<node COLOR="#00b439" CREATED="1368582383125" ID="ID_1579188902" MODIFIED="1368582393396" TEXT="ParallelCompactingCollector">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="16"/>
</node>
<node COLOR="#00b439" CREATED="1368582876309" ID="ID_1783038525" MODIFIED="1368582890794" TEXT="Concurrent Mark and Sweep (CMS) Collector">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="16"/>
</node>
<node COLOR="#00b439" CREATED="1368583151300" ID="ID_1634991078" MODIFIED="1368583209873" TEXT="&#x2013;XX:+UseSerialGC &#xa;&#x2013;XX:+UseParallelGC &#xa;&#x2013;XX:+UseParallelOldGC &#xa;&#x2013;XX:+UseConcMarkSweepGC">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="16"/>
</node>
</node>
</node>
</map>
