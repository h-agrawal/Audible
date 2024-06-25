import { AverageRating } from "./AverageRating";
import { UserReviews } from "./UserReviews";

export class Audiobook{
id!:number;
title!:string;
author!:string;
genre!:String ;
averageRatingDTO!:AverageRating;
briefDescription!:String ;
narrator!:String;
publishedDate!:Date;
releaseDate!:Date;
userReviews!:UserReviews[] ;
publisher!:String;
audioURL!:String ;
thumnailURL!:String;
sampleAudioURL!:String ;
price!:number;
}