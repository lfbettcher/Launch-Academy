import Pool from "./Pool.js";

console.log(new Pool(12, 10));
console.log(new Pool(8, 6));
console.log(new Pool(5));
console.log(new Pool(9));

let pool = new Pool(10, 15);
let anotherPool = new Pool(5);

console.log(pool.area());
console.log(anotherPool.area());
console.log(pool.volume());
console.log(anotherPool.volume());
