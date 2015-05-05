(ns wonderland
  (:require [clojure.set :as s]))

(defn common-fav-food
  [foods1 foods2]
  (let [food-set1 (set foods1)
        food-set2 (set foods2)
        common-food (s/intersection food-set1 food-set2)]
    (str "Common food: " common-food)))

(common-fav-food [:jam :brownies :toast]
                 [:lettuce :carrots :jam])
