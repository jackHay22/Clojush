;; poolgp.clj
;; Jack Hay, jhay@hamilton.edu, 2019

(ns clojush.problems.software.poolgp
  (:use [clojush.pushgp.pushgp]
        [clojush.pushstate]
        [clojush.random]
        [clojush.interpreter]
        [clojure.math.numeric-tower]))

(def argmap
  {:error-function (fn [i] i)
   :atom-generators (list (fn [] (lrand-int 10))
                          'in1
                          'integer_div
                          'integer_mult
                          'integer_add
                          'integer_sub)
   :epigenetic-markers []
   :parent-selection :tournament
   :genetic-operator-probabilities {:alternation 0.5
                                    :uniform-mutation 0.5}})
