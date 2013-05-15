/* Copyright (C) 2008-2010 University of Massachusetts Amherst,
   Department of Computer Science.
   This file is part of "FACTORIE" (Factor graphs, Imperative, Extensible)
   http://factorie.cs.umass.edu, http://code.google.com/p/factorie/
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. */

package cc.factorie.app.nlp.ner
import cc.factorie._
import cc.factorie.app.nlp._

/** The abstract class for all named-entity recognition labels. */
abstract class NerLabel(initialValue:String) extends LabeledCategoricalVariable(initialValue) {
  /** Return "PER" instead of "I-PER". */
  def shortCategoryValue: String = if (categoryValue.length > 1 && categoryValue(1) == '-') categoryValue.substring(2) else categoryValue
  def cubbieSlotName = "ner"
  def cubbieSlotValue = categoryValue
}

class NerLabelCubbie extends Cubbie {
  val label = StringSlot("label")
}

@deprecated("Use BioConllNerLabel")
abstract class ChainNerLabel(val token:Token, initialValue:String) extends NerLabel(initialValue)
@deprecated("Use ConllNerLabel")
abstract class SpanNerLabel(val span:NerSpan, initialValue:String) extends NerLabel(initialValue)


object ConllNerDomain extends CategoricalDomain[String] {
  this ++= Vector(
   "O",
   "PER", // even though this never occurs in the CoNLL-2003 training data, it could occur in some other training data
   "ORG",
   "LOC",
   "MISC"
  )
  freeze()
}
class ConllNerLabel(val token:Token, targetValue:String) extends NerLabel(targetValue) { def domain = ConllNerDomain }


object BioConllNerDomain extends CategoricalDomain[String] {
  this ++= Vector(
   "O",
   "B-PER", // even though this never occurs in the CoNLL-2003 training data, it could occur in some other training data
   "I-PER",
   "B-ORG",
   "I-ORG",
   "B-LOC",
   "I-LOC",
   "B-MISC",
   "I-MISC"
  )
  freeze()
}
class BioConllNerLabel(val token:Token, targetValue:String) extends NerLabel(targetValue) { def domain = BioConllNerDomain }


object BilouConllNerDomain extends CategoricalDomain[String] {
  this ++= Vector(
   "O",
   "B-PER",
   "I-PER",
   "L-PER",
   "U-PER",
   "B-ORG",
   "I-ORG",
   "L-ORG",
   "U-ORG",
   "B-LOC",
   "I-LOC",
   "L-LOC",
   "U-LOC",
   "B-MISC",
   "I-MISC",
   "L-MISC",
   "U-MISC"
  )  
  freeze()
}
class BilouConllNerLabel(val token:Token, targetValue:String) extends NerLabel(targetValue) { def domain = BilouConllNerDomain }


object BioOntonotesNerDomain extends EnumDomain {
  this ++= Vector(
      "O",
      "B-CARDINAL",
      "I-CARDINAL",
      "B-DATE",
      "I-DATE",
      "B-EVENT",
      "I-EVENT",
      "B-FAC",
      "I-FAC",
      "B-GPE",
      "I-GPE",
      "B-LANGUAGE",
      "I-LANGUAGE",
      "B-LAW",
      "I-LAW",
      "B-LOC",
      "I-LOC",
      "B-MONEY",
      "I-MONEY",
      "B-NORP",
      "I-NORP",
      "B-ORDINAL",
      "I-ORDINAL",
      "B-ORG",
      "I-ORG",
      "B-PERCENT",
      "I-PERCENT",
      "B-PERSON",
      "I-PERSON",
      "B-PRODUCT",
      "I-PRODUCT",
      "B-QUANTITY",
      "I-QUANTITY",
      "B-TIME",
      "I-TIME",
      "B-WORK_OF_ART",
      "I-WORK_OF_ART"
  )
  freeze()
}
class BioOntonotesNerLabel(val token:Token, targetValue:String) extends NerLabel(targetValue) { def domain = BioOntonotesNerDomain }

