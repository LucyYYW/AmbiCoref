# AmbiCoref Corpus

Given a sentence ``Abby told Brittney that she upset Courtney'', one would struggle to understand who ``she'' refers to, and ask for clarification. However, if the word ``upset'' were replaced with ``hugged'', ``she'' unambiguously refers to Abby. We study if modern co-reference resolution models are sensitive to such ambiguity.
To this end, we construct \textbf{AmbiCoref}, a diagnostic corpus of minimal sentence pairs with ambiguous and unambiguous referents.
Analysis shows that (1) humans are less sure of referents in ambiguous AmbiCoref examples than unambiguous ones, and (2) most coreference models show little difference in output between ambiguous and unambiguous pairs.
We release AmbiCoref as a diagnostic corpus for testing whether models treat ambiguity similarly to humans.
  

1. The data/sentences folder contains the AmbiCoref Corpus we generated. 
* For each type, the ambiguous templates are listed in <Type>_ambiguous.txt and the unambiguous templates are listed in <Type>_unambiguous.txt. 
* Each line contains a single sentence for the coreference resolution task. To evaluate on our AmbiCoref Corpus, run the coreference resolution system on each line of the file separately. Then compare the results between each minimal pair of ambiguous and unambiguous files.

2. The data/verb_phrases folder contains the lists of verb phrases and other necessary sentence components that are used to generate the templates. 
* For each type, each relevant list of verb phrases and sentence components is listed as a column in <Type>.csv, where the header of each column represents the corresponding template replacement slot (in curly brackets) as shown in the table below.
	* All verbs-phrases have been conjugated as required in the templates.
	* Column "Ambig." indicates whether the verb phrase is used for an ambiguous or unambiguous template. If not specified, the verb phrase/sentence component is used in both templates.
    * In IC.csv, each item {called} is compatible with any item in {reason_ambig} and {reason_unambig}.   
	* In TOP.csv,
        * {event_ambig} and {event_unambig} are strictly compatible to the {passed}, {DO} and {prep} in the same row.    
        * some phrases under header "event_amb" and "event_unamb" contain gendered pronoun. They are listed only in masculine forms for convenience but were converted to both masculine and feminine forms when generating sentences, appropriate to the Name<sub>a</sub>] and [Name<sub>b</sub>].

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


