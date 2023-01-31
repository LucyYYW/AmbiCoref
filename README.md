# AmbiCoref Corpus

Given a sentence "Abby told Brittney that she upset Courtney", one would struggle to understand who "she" refers to, and ask for clarification. However, if the word "upset" were replaced with "hugged", "she" unambiguously refers to Abby. We study if modern co-reference resolution models are sensitive to such ambiguity.
To this end, we construct \textbf{AmbiCoref}, a diagnostic corpus of minimal sentence pairs with ambiguous and unambiguous referents.
Analysis shows that (1) humans are less sure of referents in ambiguous AmbiCoref examples than unambiguous ones, and (2) most coreference models show little difference in output between ambiguous and unambiguous pairs.
We release AmbiCoref as a diagnostic corpus for testing whether models treat ambiguity similarly to humans.
  
# Data Description 
	
## Verb (phrases) 
The Data/Verb_phrases folder contains the lists of verb phrases and other necessary sentence components that are used to generate the templates. 
For each type, each relevant list of verb phrases and sentence components is listed as a column in <Type>.csv, where the header of each column represents the corresponding template replacement slot (in curly brackets) as shown in the table below.
All verbs-phrases have been conjugated as required in the templates.
Column "Ambig." indicates whether the verb phrase is used for an ambiguous or unambiguous template. If not specified, the verb phrase/sentence component is used in both templates.
In IC.csv, each item {called} is compatible with any item in {reason_ambig} and {reason_unambig}.   
In TOP.csv, {event_ambig} and {event_unambig} are strictly compatible to the {passed}, {DO} and {prep} in the same row; some phrases under header "event_amb" and "event_unamb" contain gendered pronoun. They are listed only in masculine forms for convenience but were converted to both masculine and feminine forms when generating sentences, appropriate to the Name<sub>a</sub>] and [Name<sub>b</sub>].

| File | Header         | Ambig.      | Template                                                                                                |
|------|----------------|-------------|---------------------------------------------------------------------------------------------------------|
| ECO  | bored          | ambiguous   | [Name<sub>a</sub>] told [Name<sub>b</sub>] that [pronoun] {bored} [Name<sub>c</sub>] / their colleague. |
|      | saw            | unambiguous | [Name<sub>a</sub>] told [Name<sub>b</sub>] that [pronoun] {saw} [Name<sub>c</sub>] / their colleague.   |
| ECS  | liked          | ambiguous   | [Name<sub>a</sub>] told [Name<sub>b</sub>] that [Name<sub>c</sub>] / the client {liked} [pronoun].      |
|      | met-with       | unambiguous | [Name<sub>a</sub>] told [Name<sub>b</sub>] that [Name<sub>c</sub>] / the client {met-with} [pronoun].   |
| IC   | reason_ambig   | ambiguous   | [Name<sub>a</sub>] {called} [Name<sub>b</sub>] because {reason_ambig}.                                  |
|      | reason_unambig | unambiguous | [Name<sub>a</sub>] {called} [Name<sub>b</sub>] because {reason_unambig}.                                |
|      | called         |             |                                                                                                         |
| TOP  | event_ambig    | ambiguous   | [Name<sub>a</sub>] {passed} [Name<sub>b</sub>] {DO} {prep} {event_ambig}.                               |
|      | event_unambig  | unambiguous | [Name<sub>a</sub>] {passed} [Name<sub>b</sub>] {DO} {prep} {event_unambig}.                             |
|      | passed         |             |                                                                                                         |
|      | DO             |             |                                                                                                         |
|      | prep           |             |                                                                                                         |

## NPs
The Data/NPs folder contains the names and noun phrases.
* Names.csv contains the most popular male and female names in the past five decades.
* Gendered_NPs.csv contains some common pairs of gendered pronouns or noun phrases.
* NP_mixed.csv contains a randomly generated list of pairs of male and female noun phrases.
* All_generated_NP_pairs contains the final list of noun phrases combinations used in sentence generation.  

## Sentences
The Data/Sentences folder contains the AmbiCoref Corpus we generated. 
* For each type, the ambiguous sentences are listed in <Type>_ambiguous.txt and the unambiguous sentences are listed in <Type>_unambiguous.txt. ECO sentences are further divided into two types, ECO-1 where a third name appears in the sentence and ECO-2 where a gendered pronoun is used instead. Same applies to ECS type.
* Each line contains a single sentence for the coreference resolution task. To evaluate on our AmbiCoref Corpus, run the coreference resolution system on each line of the file separately. Then compare the results between each minimal pair of ambiguous and unambiguous files.

## Human judgements
The Data/Human_judgments folder contains a subset of sentences per each type that are used for human judgements.
	
# Code Description
## Data generation
Dataset.ipynb contains script for obtaining and processing names, noun phrases and verb phrases, and generating sentences to form the AmbiCoref Corpus.
## Evaluation 
The three notebooks prefixed with "Evaluation_" respectively contains script for evaluating the three models on our generated AmbiCoref Corpus.
## Human judgements
Human_judgements.ipynb contains script for generating sub-corpus used for human judgements, and analyzing the results.
