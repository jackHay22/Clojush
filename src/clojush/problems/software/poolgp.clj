;; poolgp.clj
;; Jack Hay, jhay@hamilton.edu, 2019

(ns clojush.problems.software.poolgp
  (:use [clojush.pushgp.pushgp]
        [clojush.pushstate]
        [clojush util globals]
        [clojush.random]
        [clojush.interpreter]
        [clojure.math.numeric-tower]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; poolgp utils

(defn- second-item
  "Returns second item in stack"
  [type state]
  (let [stack (type state)]
    (if (empty? stack)
      :no-stack-item
      (second stack))))

(defn- do-vec2-op
  [op]
  (fn [v1 v2]
    [(op (first v1) (first v2))
     (op (second v1) (second v2))]))

(defn- vec2-scale
  [v1 s]
  [(* (first v1) s)
   (* (second v1 s))])

(def vec-add (do-vec2-op +))
(def vec-sub (do-vec2-op -))

(defn vec-op
  "Returns a function that takes a state and conj's an item onto the type stack."
  [vec-type op]
  (fn [state]
    (if (>= (count (vec-type state)) 2)
      (let [result (op (top-item vec-type state) (second-item vec-type state))]
        (if (>= max-vector-length (count result))
          (push-item result
                     vec-type
                     (pop-item vec-type
                        (pop-item vec-type state)))
          state))
      state)))

(defn vec-scaler
  [vec-type]
  (fn [state]
    (if (and (>= (count (vec-type state)) 1)
             (not (empty? (:integer state))))
      (let [result (vec2-scale (top-item vec-type state) (first (:integer state)))]
        (if (>= max-vector-length (count result))
          (push-item result
                     vec-type
                     (pop-item :integer
                       (pop-item vec-type state)))
          state))
      state)))

(defn vec-maker
  [vec-type num-type]
  (fn [state]
    (if (and (>= (count (vec-type state)) 1)
             (not (empty? (num-type state))))
       (let [result [(top-item num-type state) (second-item num-type state)]]
         (if (>= max-vector-length (count result))
           (push-item result
                      vec-type
                      (pop-item num-type
                      (pop-item num-type
                        (pop-item vec-type state))))
           state))
       state)))

(defn- ** [x] (* x x))

(defn vec-lengther
  [vec-type]
  (fn [state]
    (if (>= (count (vec-type state)) 1)
       (let [vec (top-item vec-type state)
             result (Math/sqrt (+ (** (first vec)) (** (second vec))))]
           (push-item (float result)
                      :float
                      (pop-item vec-type state)))
       state)))

(defn vec-dotter
  [vec-type]
  (fn [state]
    (if (>= (count (vec-type state)) 2)
       (let [vec1 (top-item vec-type state)
             vec2 (second-item vec-type state)
             result (+ (* (first vec1) (first vec2))
                       (* (second vec1) (second vec2)))]
           (push-item (float result)
                      :float
                      (pop-item vec-type
                      (pop-item vec-type state))))
       state)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; poolgp instructions

(define-registered vector_integer_add (with-meta (vec-op :vector_integer vec-add) {:stack-types [:vector_integer]}))
(define-registered vector_integer_sub (with-meta (vec-op :vector_integer vec-sub) {:stack-types [:vector_integer]}))
(define-registered vector_integer_dot (with-meta (vec-dotter :vector_integer) {:stack-types [:vector_integer]}))
(define-registered vector_integer_scale (with-meta (vec-scaler :vector_integer) {:stack-types [:vector_integer :integer]}))
(define-registered vector_integer_len (with-meta (vec-lengther :vector_integer) {:stack-types [:vector_integer]}))

(define-registered vector_float_add (with-meta (vec-op :vector_float vec-add) {:stack-types [:vector_float]}))
(define-registered vector_float_sub (with-meta (vec-op :vector_float vec-sub) {:stack-types [:vector_float]}))
(define-registered vector_float_dot (with-meta (vec-dotter :vector_float) {:stack-types [:vector_float]}))
(define-registered vector_float_scale (with-meta (vec-scaler :vector_float) {:stack-types [:vector_float :float]}))
(define-registered vector_float_len (with-meta (vec-lengther :vector_float) {:stack-types [:vector_float]}))

(define-registered vector_float_mk (with-meta (vec-maker :vector_float :float) {:stack-types [:float]}))
(define-registered vector_integer_mk (with-meta (vec-maker :vector_integer :integer) {:stack-types [:integer]}))

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
