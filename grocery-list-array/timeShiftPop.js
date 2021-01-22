const array1 = [];
const array2 = [];
for (let i = 0; i < 1000000; i++) {
  array1.push(i);
  array2.push(i);
}

timeThis(array1, array2);

function timeThis(arr1, arr2) {
  const t0 = performance.now();
  shiftArray(arr1);
  const t1 = performance.now();
  console.log(`Shift took ${t1 - t0} milliseconds.`);

  const t2 = performance.now();
  popArray(arr2);
  const t3 = performance.now();
  console.log(`Pop took ${t3 - t2} milliseconds.`);
}

function shiftArray(array) {
  while (array.length > 0) {
    array.shift();
  }
}

function popArray(array) {
  while (array.length > 0) {
    array.pop();
  }
}
