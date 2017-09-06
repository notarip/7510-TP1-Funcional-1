(ns database-test
  (:require [clojure.test :refer :all]
            [logical-interpreter :refer :all]))

(def incomplete-database "
	varon(juan).
	varon
")

(def complete-database "
	varon(juan).
	varon(pepe).
	padre(juan,pepe).
	madre(ana,juan).
	padres(X,Y,Z):-padre(X,Z),madre(Y,Z).
")

(deftest fact-test-incomplete-db
  (testing "a incomplete database should be nil"
    (is (= (evaluate-query incomplete-database "nada") nil) )
 ))

(deftest test-complete-db
  (testing "a incomplete database should be nil"
           (is (= (database-as-a-list complete-database) ["varon(juan)","varon(pepe)","padre(juan,pepe)","madre(ana,juan)","padres(X,Y,Z):-padre(X,Z),madre(Y,Z)"]) )
  ))

(deftest fact-test-wrong-query
  (testing "complete database but wrong query should be nil"
           (is (= (evaluate-query incomplete-database "nada") nil) )
  ))


(deftest check-fact-integrity-test-1
  (testing "varon should be false"
    (is (= (check-fact-integrity "varon") false))
   ))

(deftest check-fact-integrity-test-2
  (testing "varon( should be false"
           (is (= (check-fact-integrity "varon(") false))
   ))

(deftest check-fact-integrity-test-3
  (testing "varon(juan should be false"
           (is (= (check-fact-integrity "varon(juan") false))
   ))
(deftest check-fact-integrity-test-4
  (testing "varon(juan, should be false"
           (is (= (check-fact-integrity "varon(juan,") false))
  ))


(deftest check-fact-integrity-test-5
  (testing "varon(juan,pepe). should be true"
           (is (= (check-fact-integrity "varon(juan,pepe)") true))
   ))
