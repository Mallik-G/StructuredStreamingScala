package com.dmac.scalatypes

/**
  * Created by dharshekthvel on 1/11/17.
  */
object CompoundTypesInScala {

  def main(args : Array[String]) = {
    computeData(new DataReaderImpl())
  }

  // Compound types in Scala
  def computeData(data : CSVDataReader with XMLDataReader with AbstractReader) = {

  }
}


trait CSVDataReader {

  def readCSVData() = {

  }
}


trait XMLDataReader {

  def readXMLData() = {

  }
}



trait AbstractReader {

}

abstract class AbstractFileReader {

}

class DataReaderImpl extends CSVDataReader with XMLDataReader with AbstractReader {

}
class CSVReaderImpl extends AbstractFileReader with AbstractReader with CSVDataReader {

}

class XMLReaderImpl {

}