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
   :atom-generators (concat
                      (list (fn [] (lrand-int 50))
                          'cue
                          'self-balls
                          'opp-balls
                          'pockets
                          'ball-diam
                          'self-count
                          'opp-count

                          'vector_float_add
                          'vector_float_sub
                          'vector_float_dot
                          'vector_float_scale
                          'vector_float_len
                          'vector_float_mk
                          'vector_float_decomp

                          'vector_float_dup
                          'vector_float_dup_times
                          'vector_float_swap
                          'vector_float_rot
                          'vector_float_eq
                          'vector_float_stackdepth
                          'vector_float_yank
                          'vector_float_yankdup
                          'vector_float_shove
                          'vector_float_empty)

                           (registered-for-stacks
                             [:float :exec :integer :boolean])
                           ;common instrs
                           (repeat 10 'vector_float_sub)
                           (repeat 7 'self-balls)
                           (repeat 7 'cue)
                           (repeat 5 'pockets)
                           (repeat 5 'ball-diam))
   :parent-selection :lexicase
   :population-size 48
   :max-points 300
   :max-generations 100
   :max-genome-size-in-initial-program 200
   :print-csv-logs true
   :csv-log-filename "/server_logs/gen_log.csv"
   :print-timings true
   :report-simplifications 0
   :final-report-simplifications 0
   ;UMAD
   :genetic-operator-probabilities {:uniform-addition-and-deletion 1.0}
   :uniform-addition-and-deletion-rate 0.1})
