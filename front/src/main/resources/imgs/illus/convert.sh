#!/usr/bin/env bash

set -e

declare -a images

for file in *_full.png
do
    images=("${images[@]}" "${file%_full.png}")
done

for image in "${images[@]}"
do
  for res in 3840 3360 2880 2400 1920 1680 1440 1200 960
  do
    echo "${image} => $res JPEG"
    convert "${image}_full.png" -resize $res "${image}_${res}.jpg"

    echo "${image} => $res WebP"
    convert "${image}_full.png" -resize $res "${image}_${res}.webp"
  done
done
