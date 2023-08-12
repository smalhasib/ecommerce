/* eslint-disable @next/next/no-img-element */
import React from "react";
import "react-responsive-carousel/lib/styles/carousel.min.css";
import { Carousel } from "react-responsive-carousel";

const imgs = [
  {
    id: 1,
    url: "/images/images-1.jpg",
  },
  {
    id: 2,
    url: "/images/images-2.jpg",
  },
  {
    id: 3,
    url: "/images/images-3.jpg",
  },
  {
    id: 4,
    url: "/images/images-4.jpg",
  },
];
const Hero = () => {
  return (
    <React.Fragment>
      <div className="w-full h-[75vh] justify-center items-center mt-32">
        <Carousel
          showArrows={false}
          showStatus={false}
          showIndicators={true}
          infiniteLoop={true}
          showThumbs={false}
          autoPlay
          stopOnHover
        >
          {imgs.map((ig) => (
            <div
              key={ig.id}
              className="w-full flex justify-center items-center"
            >
              <img
                src={ig.url}
                alt=""
                width={100}
                height={100}
                style={{
                  width: "70%",
                  height: "75vh",
                  backgroundPosition: "center",
                  backgroundSize: "cover",
                }}
              />
            </div>
          ))}
        </Carousel>
      </div>
    </React.Fragment>
  );
};

export default Hero;
