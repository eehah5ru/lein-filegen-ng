(defproject org.clojars.eehah5ru/lein-filegen-ng :lein-v
  :description "Leiningen plugin to generate files"
  :url "http://github.com/eehah5ru/lein-filegen-ng"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :eval-in-leiningen true
  :plugins [[com.roomkey/lein-v "6.1.0"]]

  :release-tasks [["vcs" "assert-committed"]
                  ["v" "update"] ;; compute new version & tag it
                  ["vcs" "push"]])
