(ns leiningen.filegen-ng
  (:require [clojure.java.io :as io])
  (:import [java.io File FilenameFilter]))

(defn mkdirs
  [path]
  (.mkdirs (io/file path)))

(defn parent
  [path]
  (.getParentFile (io/file path)))

(defn filegen-ng [project & args]
  "Generate files from instructions in project.clj"
  (doseq [{requires :requires
           raw-data :data
           target :target
           template-fn-str :template-fn} (:filegen-ng project)]

    (mkdirs (parent target))
    (if requires (require (eval requires)))
    (let [template-fn (eval template-fn-str)
          data (reduce-kv #(let [d (eval %3)]
                             (if (instance? clojure.lang.IFn d)
                               (assoc %1 %2 (d project))
                               (assoc %1 %2 d)))
                          {}
                          raw-data)]
      (spit target (template-fn project data)))))
