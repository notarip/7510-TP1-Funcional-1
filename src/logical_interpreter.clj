(ns logical-interpreter)


(defn check-fact-integrity
  "Validate the fact semantic integrity returns false if the fact is wrong"
  [potential-fact]
  (def re #"\w+\([a-z,]*\)(?!:)")
  (not (empty? (re-matches  re  potential-fact)))
  )

(defn check-fact-rule-integrity
  "Validate the fact semantic integrity returns false if the fact is wrong"
  [potential-fact]
  (def re #"\w+\([a-zA-Z,]*\).*")
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

(defn replace-in-all-list [coll old new]
  "Given a string's coll replace all the ocurences of old with new"
  (def l [])
  (doseq [i coll]
    (def fact (clojure.string/replace i old new))
          (def l (conj l fact))
    )
  l
  )

(defn extract-rule-name [rule]
  "Given a rule like rule(a,b,c) return rule"
  (def rule-name (nth (re-matches #"(\w+)(\(.*)" rule) 1))
  rule-name
   )

(defn create-rules-map [coll]
  "Given a list with rules and facts returns rules as a map"
  (def coll-fact (filter check-rule-integrity coll ))
  (def m {})
  (doseq [i coll-fact]
          (def one-rule (split-clean i #":-"))
                (def rule(nth one-rule 0))
                (def rule-name (extract-rule-name rule))
                (def rule-param (split-clean (nth (re-matches #".*\((.*)\)" rule) 1) #","))
                (def facts (re-seq #"\w+\([A-Z,]+\)" (nth one-rule 1)))
                (def iparam 1)
                (doseq [j rule-param]
                        (def facts (replace-in-all-list facts j (str iparam)))
                                (def iparam (inc iparam))
                )
    (def m (merge
                m
                {rule-name facts}))
    )
  m
  )

(defn find-first
  [f coll]
  (first (filter f coll))
  )

(defn execute-fact-query [query coll-fac]
  "Given a query and facs evaluate if is true or false"
  (def m (map #(= % query) coll-fac))
  (def result (not (every? false? m)))
  result
  )

(defn extract-query-params [query]
  "Given a query like rule(a,b,c) return a list (a b c)"
  (def params (nth (re-matches #"\w+\((.*)\)" query) 1))
  (def params (split-clean params #","))
  params
  )

(defn execute-rule-query [query coll-fac m-rule]
  "Given a query, facts and rules evaluete if is true or false"
  (def rule-name (extract-rule-name query))
  (def params (extract-query-params query))
  (def facts (get m-rule rule-name))
  (def iparam 1)
  (doseq [i params]
    (def facts (replace-in-all-list facts (str iparam) i ))
    (def iparam (inc iparam))
    )

  (def m (map #(execute-fact-query % coll-fac) facts))

  (def every-true (every? true? m))
  (def result every-true)
  (and result (= false (empty? facts)))
  )

(defn execute-query [query coll-fac m-rule]
  "Given a query, facs and rules evaluate if is true or false"

  (println "executing query...")
  (def fact1 (execute-fact-query query coll-fac))
  (def rule (execute-rule-query query coll-fac m-rule))

  (println "fact -> " fact1)
  (println "rule -> " rule)

  (or fact1 rule)
  )

(defn evaluate-query
  "Returns true if the rules and facts in database imply query, false if not. If
  either input can't be parsed, returns nil"
  [database query]
  (def coll (database-as-a-list database))
  (def m (map check-fact-rule-integrity coll))

  ;  (if (every? false? m) nil)
 ; (if (not (check-fact-integrity query)) nil)

  (def evaluate-query-response nil)
  (when (every? false? m)
    (when-not (check-fact-integrity query)
      (def coll-facts (map str (filter-facts coll)))
              (def m-rules (create-rules-map coll ))
              (def evaluate-query-response (execute-query query coll-facts m-rules))
      )
    )
   evaluate-query-response
  )


