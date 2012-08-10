(ns t-nein.core
  (:use [clojure.string :only [upper-case]])
  (:require [lanterna.screen :as s])
  (:gen-class))

(defn echo [screen]
  (s/clear screen)
  (s/in-screen screen
               (loop [cnt 0
                      c (str (s/get-key-blocking screen))]
                 (s/put-string screen cnt 0 c)
                 (s/put-string screen cnt 1 (upper-case c))
                 (s/redraw screen)
                 (recur (inc cnt) (str (s/get-key-blocking screen))))))

(defn -main
  "A simple echo server, for now..."
  [& args]
  (echo (s/get-screen :swing)))
