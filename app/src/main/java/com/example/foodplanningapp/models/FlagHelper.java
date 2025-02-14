package com.example.foodplanningapp.models;

public class FlagHelper {
    public  static String getUrl(String area)
    {
        String name="";
        switch (area)
        {
            case "American":
                name="us";
                break;

            case "British":
                name="gb";
                break;

            case "Chinese":
                name="cn";
                break;

            case "Croatian":
                name="hr";
                break;

            case "Dutch":
                name="nl";
                break;

            case "Filipino":
                name="ph";
                break;

            case "Irish":
                name="ie";
                break;

            case "Jamaican":
                name="jm";
                break;
            case "Japanese":
                name="jp";
                break;


            case "Malaysian":
                name="my";
                break;

            case "Mexican":
                name="mx";
                break;
            case "Moroccan":
                name="ma";
                break;

            case "Polish":
                name="pl";
                break;

            case "Portuguese":
                name="pt";
                break;
            case "Spanish":
                name="es";
                break;

            case "Tunisian":
                name="tn";
                break;

            case "Turkish":
                name="tr";
                break;

            case "Ukrainian":
                name="ua";
                break;

            case "Uruguayan":
                name="uy";
                break;

            case "Vietnamese":
                name="vn";
                break;

            default:
                name=area.substring(0,2);
                break;
        }

        name=name.toLowerCase();
        return "https://www.themealdb.com/images/icons/flags/big/64/"+name+".png";
    }

}
