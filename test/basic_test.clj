(ns basic-test
  (:require [clojure.test :refer :all]
            [logical-interpreter :refer :all]))

(def incomplete-database "
	varon(juan).
	varon
")

(deftest query-as-a-list-test
  (testing "add(one,two,one) should be [add,one,two,one]"
    (is (= (evaluate-query incomplete-database"add(one,two,one)")
           ["add" "one" "two" "one"])))
  )
