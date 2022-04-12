package br.unb.cic

import java.io.File
import java.io.PrintWriter

import scala.io.Source

import org.backuity.clist._

import br.unb.cic.TFTheOne._


object MainProgram extends CliMain[Unit] (

  name="Word Count",
  description="a simple word count implementation using the \"TheOne\" style") {

  var inputFile = arg[String](description = "path to the input file")
  var stopWordsFile = arg[String](description = "path to the stop words file")
  var n  = opt[Int](default = 20)

  def run: Unit = {
    val stopWords = Source.fromFile(stopWordsFile).getLines.toSet

    val res: List[(String, Int)] =
      TFTheOne(inputFile)
        .bind(readFile)
        .bind(scan)
        .bind(filterChars)
        .bind(normalize)
        .bind(removeStopWords(stopWords))
        .bind(frequencies)
        .bind(sort)
        .getValue()

    for((w, c) <- res.take(n)) {
      println(s"$w - $c")
    }
  }
}
