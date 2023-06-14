import React from "react";
import styles from "./essential.module.css";
const ExtraDiv = ({ data }: { data: string }) => {
  return (
    <div className={styles.extra_div}>
      <div className="w-full h-[25vh] bg-[#000000b3] rounded-b-[3rem] flex justify-center items-center">
        <h1 className="text-3xl font-bold text-white">{data}</h1>
      </div>
    </div>
  );
};

export default ExtraDiv;
