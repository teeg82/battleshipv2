#!/bin/bash

docker run \
    -it \
    --name battleship2 \
    --rm \
    --mount type=bind,source="$(pwd)",target=/battleship \
    paulrichter/battleship2:latest \
    $@
