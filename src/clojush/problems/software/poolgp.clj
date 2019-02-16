;; poolgp.clj
;; Jack Hay, jhay@hamilton.edu, 2019

(ns clojush.problems.software.poolgp
  (:use [clojush.pushgp.pushgp]
        [clojush.pushstate]
        [clojush util globals]
        [clojush.random]
        [clojush.interpreter]
        [clojure.math.numeric-tower]))

(def argmap
  {:error-function identity
   :atom-generators (list (fn [] (lrand-int 50))
                          'cue
                          'self-balls
                          'opp-balls
                          'pockets
                          'float_div
                          'float_mult
                          'float_add
                          'float_sub
                          'vector_float_add
                          'vector_float_sub
                          'vector_float_dot
                          'vector_float_scale
                          'vector_float_len
                          'vector_float_mk
                          'float_mod
                          'float_lt
                          'float_lte
                          'float_gt
                          'float_gte)
   :epigenetic-markers []
   :parent-selection :tournament
   :population-size 5
   :genetic-operator-probabilities {:alternation 0.5
                                    :uniform-mutation 0.5}})
