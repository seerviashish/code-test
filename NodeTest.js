const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];

rl.on("line", (lineInput) => {
  input.push(lineInput);
});

rl.on("close", () => {
  const test = parseInt(input[0]);
  for (let t = 0; t < test; t++) {
    const n = parseInt(input[1]);
    print(n);
  }
});

const print = (value) => {
  console.log("\nResult: ", value);
};
