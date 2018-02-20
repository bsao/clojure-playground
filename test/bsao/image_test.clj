(ns bsao.image-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [bsao.image :refer :all]))

(deftest test-image-width
  (testing "We should be able to get the image width"
    (let [image-stream (io/input-stream "http://imgs.xkcd.com/comics/angular_momentum.jpg")
          image (load-image image-stream)]
      (save-image image "xkcd-width.png")
      (is (= 600 (get-image-width (io/input-stream "xkcd-width.png")))))))

(deftest test-image-width-str-arg
  (testing "We should be able to get the image width"
    (let [image-stream "http://imgs.xkcd.com/comics/angular_momentum.jpg"
          image (load-image image-stream)]
      (save-image image "xkcd-width.png")
      (is (= 600 (get-image-width (io/input-stream "xkcd-width.png")))))))

(deftest test-load-image
  (testing "We should be able to generate thumbnails"
    (let [image-stream (io/input-stream "http://imgs.xkcd.com/comics/angular_momentum.jpg")
          image (load-image image-stream)
          thumbnail-image (generate-thumbnail image 50)]
      (save-image thumbnail-image "xkcd.png")
      (is (= 50 (get-image-width (io/input-stream "xkcd.png")))))))