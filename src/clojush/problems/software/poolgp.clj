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
                          'ball-diam
                          'self-count
                          'opp-count

                          'float_div
                          'float_mult
                          'float_add
                          'float_sub
                          'integer_div
                          'integer_mult
                          'integer_add
                          'integer_sub
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
                          'float_gte
                          (registered-for-stacks [:integer :float :boolean :exec]))
   :parent-selection :lexicase
   :population-size 50
   :max-points 3200
   :max-generations 100
   :max-genome-size-in-initial-program 400
   :print-csv-logs true ;path specified as arg
   :print-timings true
   :report-simplifications 0
   :final-report-simplifications 0
   ;UMAD
   :genetic-operator-probabilities {:uniform-addition-and-deletion 1.0}
   :uniform-addition-and-deletion-rate 0.1})
