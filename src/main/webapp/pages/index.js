import HomeHeader from "../components/HomeHeader.js";
import HomeGrid from "../components/HomeGrid.js";
import Head from "next/head";


var content = (
  <div>
    <Head>
      <title> Stingy | Price comparison! </title>
      <link href="/static/indexStyle.css" rel="stylesheet" />
      <link rel="shortcut icon" href="../static/logo/logo.png"/>
    </Head>
    <HomeHeader />,
    <HomeGrid />
  </div>
);
export default () => content;
