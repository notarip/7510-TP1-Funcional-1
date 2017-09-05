(ns basic-test
  (:require [clojure.test :refer :all]
            [logical-interpreter :refer :all]))

(def database "
	varon(juan).
	varon(pepe).
	padre(juan,pepe).
")

(deftest query-as-a-list-test
  (testing "add(one,two,one) should be [add,one,two,one]"
    (is (= (query-as-a-list "add(one,two,one)")
           ["add" "one" "two" "one"])))
  )

(deftest database-as-a-list-test
  (testing "add(one,two,one) should be [add,one,two,one]"
           (is (= (database-as-a-list database)
                  ["varon(juan)" "varon(pepe)" "padre(juan,pepe)"])))
  )

(deftest rule-as-a-list-test
  (testing "rule(X,Y) :- fact1(Y,X),fact2(X). should be [rule, X, Y, fact1, X, Y, fact2, X]"
           (is (= (database-as-a-list database)
                  ["varon(juan)" "varon(pepe)" "padre(juan,pepe)"])))
  )

