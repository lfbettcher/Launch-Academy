for (i = 99; i > 0; i--) {
    let next = i === 1 ? "no more" : i - 1
    let punc = i % 10 === 0 ? "!" : "."
    let plural1 = i === 1 ? "" : "s"
    let plural2 = next === 1 ? "" : "s"

    console.log(`${i} bottle${plural1} of beer on the wall, ${i} bottle${plural1} of beer${punc}\n` +
                `Take one down and pass it around, ${next} bottle${plural2} of beer on the wall.\n\n`)
}

console.log("No more bottles of beer on the wall, no more bottles of beer.\n" + 
            "Go to the store and buy some more, 99 bottles of beer on the wall.\n\n")