import Link from "next/link";
import React from "react";

const Home = () => {
  return (
    <div>
      <ul>
        <li>
          <Link href="/users">Users</Link>
        </li>
      </ul>
    </div>
  );
};

export default Home;
