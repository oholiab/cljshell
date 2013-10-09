(ns cljshell.core
  (:use server.socket))

(import '[java.io BufferedReader InputStreamReader OutputStreamWriter])
(import '[java.lang System])

(def port 7888)

(def oldout *out*)

(defn console [msg]
  (binding [*out* oldout]
    (println msg)))

(defn echo-server [in out]
  (while true
    (binding [*in* (BufferedReader. (InputStreamReader. in))
             *out* (OutputStreamWriter. out)]
      (let [line (read-line)]
        (try
          (console (str ">> " line))
          (let [return-val (load-string line)]
            (console return-val)
            (println return-val))
          (catch Exception e (do (console e)
                                 (println e))))
      (.close *out*)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (println "Hello, World!")
  (create-server port echo-server))
