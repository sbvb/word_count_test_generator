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



val returnValue = myTimeMeasure {
  val lines = sc.textFile("file:/home/sbvb/zzz/*.txt")
  val words = lines.flatMap(_.split(" ")).filter(_ != "")
  println("Number of words="+words.count())
  val counts = words.map(word => (word,1))
  val countsReduced = counts.reduceByKey((a,b) => a+b)
  // sortByKey(false) => sort descending
  val countsReducedInv = countsReduced.map(x => (x._2,x._1))
  //val countsReducedInv = countsReduced.map(x => x.swap)
  countsReducedInv.sortByKey(false).take(10).foreach(println)
}



// spark measure time test
// val returnValue = myTimeMeasure { 1 to 1000 sum }
// println("returnValue="+ returnValue)

