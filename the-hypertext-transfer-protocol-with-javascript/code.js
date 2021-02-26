import request from "request";
import fs from "fs";

request(
  "https://s3.amazonaws.com/horizon-production/images/http-request-response-cycle.png",
  { encoding: null },
  (error, response, body) => {
    console.log(response.statusCode);
    console.log(response.headers["content-type"]);
    fs.writeFileSync("image.png", body);
  }
);
