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
  {:error-function (fn [i] i)
   :atom-generators (list (fn [] (lrand-int 10))
                          ;balls
                          'in1
                           'in2
                           'in3
                           'in4
                           'in5
                           'in6
                           'in7
                           'in8
                          ; 'in9
                          ; 'in10
                          ; 'in11
                          ; 'in12
                          ; 'in13
                          ; 'in14
                          ; 'in15
                          ; 'in16
                          ; ;pockets
                          ; 'in17
                          ; 'in18
                          ; 'in19
                          ; 'in20
                          ; 'in21
                          ; 'in22
                          'integer_div
                          'integer_mult
                          'integer_add
                          'integer_sub
                           ;'vector_integer_add
                           ;'vector_integer_sub
                          ; 'vector_integer_dot
                          ; 'vector_integer_scale
                          ; 'vector_integer_len
                          ; 'vector_float_add
                          ; 'vector_float_sub
                          ; 'vector_float_dot
                          ; 'vector_float_scale
                          ; 'vector_float_len
                          'vector_float_mk
                          'vector_integer_mk
                          )
   :epigenetic-markers []
   :parent-selection :tournament
   :population-size 50
   :genetic-operator-probabilities {:alternation 0.5
                                    :uniform-mutation 0.5}})
