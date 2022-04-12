package br.unb.cic

import scala.collection.immutable.ListMap
import scala.io.Source
import scala.collection.mutable.Map

case class TFTheOne[A](value: A) {
  def bind[B](f: A => B): TFTheOne[B] = TFTheOne(f(value))

  def getValue(): A = value
}

object TFTheOne {
  def readFile(path: String) : List[String] = Source.fromFile(path).getLines.toList

  def scan(lines: List[String]): List[String] = lines.flatMap(line => line.split(" "))

  def filterChars(words: List[String]): List[String] = words.map(w => w.replaceAll("[^a-zA-Z]", ""))

  def normalize(words: List[String]): List[String] = words.map(w => w.toLowerCase)

  def removeStopWords(stopWords: Set[String])(words: List[String]): List[String] = words.filter(w => !stopWords.contains(w))

  def frequencies(words: List[String]): Map[String, Int]  = {
    val res: Map[String, Int] = Map()

    for(w <- words) {
      val count = res.getOrElse(w, 0)
      res += (w -> (count + 1))
    }
    res
  }

  def sort(mapping: Map[String, Int]): List[(String, Int)] = ListMap(mapping.toSeq.sortWith(_._2 > _._2):_*).toList

}
