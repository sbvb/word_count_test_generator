// word count in Scala

def myTimeMeasure[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block    // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0)/1000.0 + "us")
    println("Elapsed time: " + (t1 - t0)/1000000.0 + "ms")
    println("Elapsed time: " + (t1 - t0)/1000000000.0 + "s")
    result
}



// 

val returnValue = myTimeMeasure {
  val lines = sc.textFile("hdfs:/user/root/maxword3000/*.txt")
  val words = lines.flatMap(_.split(" ")).filter(_ != "")
  println("Number of words="+words.count())
  val counts = words.map(word => (word,1))
  val countsReduced = counts.reduceByKey((a,b) => a+b)
  // sortByKey(false) => sort descending
  val countsReducedInv = countsReduced.map(x => (x._2,x._1))
  //val countsReducedInv = countsReduced.map(x => x.swap)
  countsReducedInv.sortByKey(false).take(10).foreach(println)
}



/****************************************************************
result for aws spark cluster, 1 node
Number of words=4501500                                                         
(3000,a3000)                                                                    
(2999,a2999)
(2998,a2998)
(2997,a2997)
(2996,a2996)
(2995,a2995)
(2994,a2994)
(2993,a2993)
(2992,a2992)
(2991,a2991)
Elapsed time: 6.30087241922E8us
Elapsed time: 630087.241922ms
Elapsed time: 630.087241922s


result for aws spark cluster, 10 nodes
Number of words=4501500                                                         
(3000,a3000)                                                                    
(2999,a2999)
(2998,a2998)
(2997,a2997)
(2996,a2996)
(2995,a2995)
(2994,a2994)
(2993,a2993)
(2992,a2992)
(2991,a2991)
Elapsed time: 1.63868013661E8us
Elapsed time: 163868.013661ms
Elapsed time: 163.868013661s
****************************************************************/

