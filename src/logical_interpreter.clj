(ns logical-interpreter)


(defn check-fact-integrity
  "Validate the fact semantic integrity"
  [potential-fact]
  (def re #"\w+\(.+\)$")
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
  (def coll-facts (filter check-fact-integrity coll))
  (println coll-facts)
  )


