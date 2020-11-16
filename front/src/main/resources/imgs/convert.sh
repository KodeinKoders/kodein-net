#!/usr/bin/env bash

workdir=$1
if [ "$workdir" == "" ]; then
    workdir='illus'
fi

originFormat=$2
if [ "$originFormat" == "" ]; then
    originFormat='png'
fi

set -e

declare -a images

for file in $workdir/*_full.$originFormat
do
    images=("${images[@]}" "${file%_full.$originFormat}")
done

for image in "${images[@]}"
do
  for res in 3840 3360 2880 2400 1920 1680 1440 1200 960
  do
    echo "${image} => $res JPEG"
    convert "${image}_full.$originFormat" -resize $res "${image}_${res}.jpg"

    echo "${image} => $res WebP"
    convert "${image}_full.$originFormat" -resize $res "${image}_${res}.webp"
  done
done
