(ns reader-test
  (:require [clojure.test :refer :all]
            [logical-interpreter :refer :all]))



(deftest read-hole-file-test
  (testing "reading a hole file in memory"
    (is (= (load-hole-file "resources/hole-test.txt")
           "This\nis\nthe\nhole\nfile.\n"
           )))
  )

(deftest read-by-line-test
  (testing "converting a file lines in a list"
           (is (= (read-file-as-list "resources/hole-test.txt") ["This","is", "the","hole", "file."] )))
  )
