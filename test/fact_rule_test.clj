(ns fact-rule-test
  (:require [clojure.test :refer :all]
            [logical-interpreter :refer :all]))

(def incomplete-database "
	varon(juan).
	varon
")

(def complete-database "
	varon(bart).
	varon(homero).
	mujer(march).
	padre(homero,bart).
	madre(march,bart).
	padres(X,Y,Z):-padre(X,Z),madre(Y,Z).
	tios(X,Y,Z):-padre(X,Z),madre(Y,Z).
")

(deftest fact-rule-separation-test
  (testing "separating facts and rules in differents lists"
    (is (= (evaluate-query complete-database "varon(bart)") true))
 ))

(deftest fact-true-test
  (testing "quering for a fact that exists"
           (is (= (evaluate-query complete-database "varon(bart)") true))
        )
  )

(deftest fact-false-test
  (testing "quering for a fact that doesn't exists"
           (is (= (evaluate-query complete-database "varon(pablo)") false))
           )
  )

(deftest rule-true-test
  (testing "quering for a rule that  exists"
   (is (= (evaluate-query complete-database "padres(homero,march,bart)") true))
     )
  )
(deftest rule-false-test
  (testing "quering for a rule that doesn't exists"
           (is (= (evaluate-query complete-database "padres(homero,march,apu)") false))
           )
  )
