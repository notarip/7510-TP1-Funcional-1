(ns logical-interpreter)


(defn check-fact-integrity
  "Validate the fact semantic integrity"
  [potential-fact]
  (def re #"\w+\(\w+,\w+\)(?!:)")
  (not (empty? (re-matches  re  potential-fact)))
  )

(defn check-rule-integrity
  "Validate the rule semantic integrity"
  [potential-rule]
  (def re #"\w+\(.+\):-(\w+\(.+\))+")
  (not (empty? (re-matches  re  potential-rule)))
  )

(defn split-clean [chain re]
  "Given a chain split by the expresion and clean the whities"
  (remove empty? (clojure.string/split chain re))
  )

(defn query-as-a-list
  "Returns the query as a list"
  [query]
  (split-clean query #",|\(|\)")
  )


(defn database-as-a-list
  "Returns the database as a list"
  [database]
  (split-clean database #"\.|\n|\t")
  )


(defn load-hole-file [path-file]
  "Reads a hole file in memory"
  (use 'clojure.java.io)
  (slurp path-file)
  )


(defn read-file-as-list [path-file]
  "Reads a hole file as a list"
  (split-clean (load-hole-file path-file) #"\n")
  )

(defn filter-facts [coll-fact]
  "Given a list with rules and facts returns a facts list"
  (filter check-fact-integrity coll-fact)
  )

(defn create-rules-map [coll]
  "Given a list with rules and facts returns rules as a map"
  (def coll-fact (filter check-rule-integrity coll ))
  (println "rules...")
  (doseq [i coll-fact]
  (println i)
          (def one-rule (split-clean i #":-"))
    (println one-rule)
    (def rule(nth one-rule 0))
    (def rule-param (split-clean (nth (re-matches #".*\((.*)\)" rule) 1) #","))
    (def facts(split-clean (nth one-rule 1) #","))
    (println rule)
    (println rule-param)
    (println facts)
    )
  (println "end rules...")
  ;recorrer las rules, mergewhith
    ;separar rule y facts
  ;separar la rule
  ;split de los parametros
  ;split de las rules
  ;recorrer los parametros y reempazar en las rules con nros i
  )

(defn evaluate-query
  "Returns true if the rules and facts in database imply query, false if not. If
  either input can't be parsed, returns nil"
  [database query]
  (def coll (database-as-a-list database))
  (println coll)
  (def m (map check-fact-integrity coll))
  ;(println coll)
  ;(println m)
  (if (every? false? m) nil)
  (if (not (check-fact-integrity query)) nil)

  (def coll-facts (filter-facts coll))
  (def m-rules (create-rules-map coll ))


  (println coll-facts)
  (println m-rules)
  )


