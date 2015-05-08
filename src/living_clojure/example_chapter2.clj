(ns wonderland)
;;-> nil

(defn drinkable? [x]
  (= x :drinkme))
;;-> #'wonderland/drinkable?

;; every?

(every? drinkable? [:drinkme :drinkme])
;;-> true

(every? drinkable? [:drinkme :poison])
;;-> false

(every? (fn [x] (= x :drinkme)) [:drinkme :drinkme])
;;-> true

(every? #(= % :drinkme) [:drinkme :drinkme])
;;-> true

;; not-any?
(not-any? #(= % :drinkme) [:drinkme :poinson])
;;-> false

(not-any? #(= % :drinkme) [:poison :poison])
;;-> true

;; some
(some #(> % 3) [1 2 3 4 5])
;;-> true

(some #(> % 3) [0 1 2 3])
;;-> nil

(#{1 2 3 4 5} 3)
;;-> 3

;; Beware of logically false values
(some #{nil} [nil nil nil])
;;-> nil

(some #{false} [false false false])
;;-> nil

;; HARNESSING THE POWER OF FLOW CONTROL

;; if

(if true "it is true" "it is false")
;;-> "it is true"

(if false "it is true" "it is false")
;;-> "it is false"

(if nil "it is true" "it is false")
;;-> "it is false"

(if (= :drinkme :drinkme)
  "Try it"
  "Don't try it!")
;;-> "Try it"

;; if-let
(let [need-to-grow-small (> 5 3)]
  (if need-to-grow-small
    "drink bottle"
    "don't drink bottle"))
;;-> "drink bottle"

(if-let [need-to-grow-small (> 5 3)]
  "drink bottle"
  "don't drink bottle")
;;-> "drink bottle"

;; when
(defn drink [need-to-grow-small]
  (when need-to-grow-small "drink bottle"))
;;-> #'wonderland/drink

(drink true)
;;-> "drink bottle"

(drink false)
;;-> nil

;; when-let
(when-let [need-to-grow-small true]
  "drink bottle")
;;-> "drink bottle"

(when-let [need-to-grow-small false]
  "drink bottle")
;;-> nil

;; cond
(let [bottle "drinkme"]
  (cond
    (= bottle "poison") "don't touch"
    (= bottle "drinkme") "grow smaller"
    (= bottle "empty") "all gone"))
;;-> "grow smaller"

;; cond - order matters
(let [x 5]
  (cond
    (> x 3) "bigger than 3"
    (> x 4) "bigger than 4"
    (> x 10) "bigger then 10"))
;;-> "bigger than 3"

(let [x 5]
  (cond
    (> x 4) "bigger than 4"
    (> x 3) "bigger than 3"
    (> x 10) "bigger than 10"))
;;-> "bigger than 4"

;; cond with :else keyword
(let [bottle "mystery"]
  (cond
    (= bottle "poison") "don't touch"
    (= bottle "drinkme") "grow smaller"
    (= bottle "empty") "all gone"
    :else "unknown"))
;;-> "unknown"

;; case
(let [bottle "drinkme"]
  (case bottle
    "poison" "don't touch"
    "drinkme" "grow smaller"
    "empty" "all gone"))
;;-> "grow smaller"

;; case - Exception if none of tests match
(let [bottle "mystery"]
  (case bottle
    "poison" "don't touch"
    "drinkme" "grow smaller"
    "empty" "all gone"))
;;-> IllegalArgumentException

;; FUNCTIONS CREATING FUNCTIONS AND OTHER
(defn grow [name direction]
  (if (= direction :small)
    (str name " is growing smaller")
    (str name " is growing bigger")))
;;-> #'wonderland/grow

(grow "Alice" :small)
;;-> "Alice is growing smaller"

(grow "Alice" :big)
;;-> "Alice is growing bigger"

;; partial application
(partial grow "Alice")
;;-> #<core$partial$fn__4228 clojure.core$partial$fn__4228@70be7da3>

((partial grow "Alice") :small)
;;-> "Alice is growing smaller"

;; function composition
(defn toogle-grow
  [direction]
  (if (= direction :small) :big :small))
;;=> #'wonderland/toogle-grow

(defn oh-my
  [direction]
  (str "Oh my! You're growing " direction))
;;=> #'wonderland/oh-my

(oh-my (toogle-grow :small))
;;=> "Oh my! You're growing :big"

(defn surprise
  [direction]
  ((comp oh-my toogle-grow) direction))
;;-> #'wonderland/surprise

(surprise :small)
;;-> "Oh my! You're growing :big"

;; DESTRUCTURING
(let [[color size] ["blue" "small"]]
  (str "The " color " door is " size))
;;-> "The blue door is small"

;; without destructuring, the code is more verbose
(let [x ["blue" "small"]
      color (first x)
      size (last x)]
  (str "The " color " door is " size))
;;-> "The blue door is small"

;; let's keep the whole initial data structure as binding
(let [[color [size] :as original] ["blue" ["small"]]]
  {:color color :size size :original original})
;;-> {:color "blue", :size "small", :original ["blue" ["small"]]}

;; destructuring can be also applied to maps
(let [{flower1 :flower1 flower2 :flower2}
      {:flower1 "red" :flower2 "blue"}]
  (str "The flowers are " flower1 " and " flower2))
;;-> "The flowers are red and blue"

;; We can also u
