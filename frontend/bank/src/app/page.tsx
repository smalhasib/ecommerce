import Login from "@/components/Auth/Login";
import Link from "next/link";
import React from "react";
import { Lato } from "@next/font/google";
const lato = Lato({
  weight: "400",
  subsets: ["latin"],
});
const Home = () => {
  return (
    <React.Fragment>
      <div className={`w-full flex justify-center items-center`}>
        <section className="w-[75%] text-gray-600 body-font">
          <div className="container mx-auto flex px-10 py-20 md:flex-row flex-col items-center">
            <div className="lg:flex-grow lg:w-full md:w-1/2 lg:pr-24 md:pr-16 flex flex-col md:items-start md:text-left mb-16 md:mb-0 items-center text-center">
              <h1
                className={`title-font sm:text-4xl text-3xl mb-4 font-medium text-gray-900 ${lato.className}`}
              >
                Here We are,
                <br className="hidden lg:inline-block" />
                Keep Your Money Safe.
              </h1>
              <p className={`mb-8 leading-relaxed ${lato.className}`}>
                Copper mug try-hard pitchfork pour-over freegan heirloom neutra
                air plant cold-pressed tacos poke beard tote bag. Heirloom echo
                park mlkshk tote bag selvage hot chicken authentic tumeric
                truffaut hexagon try-hard chambray.
              </p>
              <div className="flex justify-center">
                <button className="inline-flex text-white bg-[#31ABFC] hover:bg-blue-600 border-0 py-2 px-6 focus:outline-none rounded text-lg">
                  Get started
                </button>
              </div>
            </div>
            <div className="lg:max-w-full lg:w-full md:w-1/2 w-5/6">
              <img
                className="object-cover object-center w-full h-[55vh] rounded-lg"
                alt="hero"
                src="/images/bank.jpg"
              />
            </div>
          </div>
        </section>
      </div>
    </React.Fragment>
  );
};

export default Home;
