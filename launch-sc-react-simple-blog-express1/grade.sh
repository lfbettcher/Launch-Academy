#! /usr/bin/env zsh

echo "{
  'presets': ['env', 'react']
}" > .babelrc
chmod -R 755 ./
yarn install
bundle