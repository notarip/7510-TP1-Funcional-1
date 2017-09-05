(ns logical-interpreter)

(defn evaluate-query
  "Returns true if the rules and facts in database imply query, false if not. If
  either input can't be parsed, returns nil"
  [database query]
  (query-as-a-list query)
  )


(defn query-as-a-list
  "Returns the query as a list"
  [query]
  (clojure.string/split query #",|\(|\)")
  )