package com.nativesquad.guiadeviajes.models;

import com.nativesquad.guiadeviajes.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Proveedor de datos estructurados para los destinos turísticos de Colombia.
 */
public class DestinosData {

    public static List<Destino> getDestinos() {
        List<Destino> destinos = new ArrayList<>();

        destinos.add(new Destino(
                1,
                "Cartagena de Indias",
                "Ciudad Amurallada & Costa Caribe",
                "Bolívar - Región Caribe",
                R.drawable.ic_cartagena,
                "Fundada en 1533 por Pedro de Heredia, Cartagena fue el puerto neurálgico del comercio colonial español en América del Sur. Sus murallas y el imponente Castillo de San Felipe de Barajas fueron edificados para proteger los tesoros del imperio de ataques de corsarios y piratas.",
                "Ubicada a orillas del mar Caribe en el norte de Colombia (10°24′N 75°30′O). Posee un clima cálido tropical con una temperatura promedio anual de 28°C y hermosas bahías naturales protegidas por arrecifes.",
                "• Recorrido por el centro histórico y las murallas al atardecer\n• Visita al Castillo San Felipe y Convento de la Popa\n• Excursión a las Islas del Rosario y Playa Blanca\n• Gastronomía caribeña en el barrio Getsemaní",
                "Cálido Tropical (28°C - 32°C)"
        ));

        destinos.add(new Destino(
                2,
                "Eje Cafetero",
                "Paisaje Cultural Cafetero & Valle del Cocora",
                "Quindío / Risaralda / Caldas - Región Andina",
                R.drawable.ic_cafetero,
                "Declarado Patrimonio Mundial de la Humanidad por la UNESCO en 2011, este territorio representa un ejemplo excepcional de paisaje cultural productivo y sostenible, cuna de las tradiciones campesinas y la recolección artesanal del café más suave del mundo.",
                "Situado en la cordillera central andina colombiana a alturas entre los 1.200 y 2.400 msnm. Alberga el santuario natural del Valle del Cocora, hogar de la majestuosa Palma de Cera, el árbol nacional de Colombia.",
                "• Caminata ecológica por el bosque de niebla y Valle del Cocora\n• Tour del café y cata en haciendas tradicionales\n• Visita a los pueblos coloniales de Salento y Filandia\n• Termales de Santa Rosa de Cabal",
                "Templado de Montaña (17°C - 22°C)"
        ));

        destinos.add(new Destino(
                3,
                "Parque Nacional Tayrona",
                "Selva Tropical & Playas Vírgenes",
                "Magdalena - Sierra Nevada de Santa Marta",
                R.drawable.ic_tayrona,
                "Territorio sagrado ancestral habitado históricamente por la civilización Tayrona (Koguis, Wiwas, Arhuacos y Kankuamos). Conserva ruinas arqueológicas de Pueblito Chairama y senderos sagrados de gran relevancia espiritual.",
                "Ubicado en las faldas de la Sierra Nevada de Santa Marta descendiendo abruptamente hasta el mar Caribe. Su geografía combina acantilados de roca granítica, ensenadas de aguas cristalinas y densa vegetación selvática.",
                "• Senderismo desde Cañaveral hasta Cabo San Juan de la Guía\n• Snorkel y avistamiento de fauna en La Piscina y Arrecifes\n• Observación de aves endémicas y monos aulladores\n• Alojamiento en eco-habs tradicionales",
                "Tropical Húmedo (27°C - 30°C)"
        ));

        destinos.add(new Destino(
                4,
                "San Andrés y Providencia",
                "Archipiélago & Mar de los Siete Colores",
                "Región Insular",
                R.drawable.ic_sanandres,
                "Declarado Reserva Mundial de la Biosfera 'Seaflower', el archipiélago cuenta con una rica herencia cultural raizal que mezcla raíces afrocaribeñas, inglesas e hispanas. Fue refugio del célebre corsario Henry Morgan en el siglo XVII.",
                "Conjunto de islas e islotes ubicados a 775 km al noroeste de la costa continental colombiana en pleno Mar Caribe occidental. Rodeado por la tercera barrera de coral más extensa del planeta.",
                "• Vuelta a la isla en carrito de golf pasando por el Hoyo Soplador\n• Buceo de pared y careteo en Johnny Cay y Haynes Cay\n• Recorrido por los manglares del Parque Regional Old Point\n• Noche de reggae y calipso en la playa",
                "Cálido Marítimo (29°C constante)"
        ));

        destinos.add(new Destino(
                5,
                "Caño Cristales",
                "El Río de los Cinco Colores",
                "Meta - Sierra de la Macarena",
                R.drawable.ic_cristales,
                "Conocido internacionalmente como 'el río más hermoso del planeta' o 'el arcoíris líquido'. En sus lechos rocosos florece la planta endémica Macarenia clavigera, produciendo tapices acuáticos de tonos fucsia, amarillo, verde, azul y negro.",
                "Ubicado en el Parque Nacional Natural Sierra de la Macarena, una meseta precámbrica independiente de los Andes considerada uno de los refugios de biodiversidad más antiguos de Sudamérica.",
                "• Baño recreativo en piscinas naturales como Los Hoyos y El Tapete\n• Caminatas por cascadas y formaciones rocosas milenarias\n• Avistamiento de fauna llanera y delfines de río en el río Guayabero\n• Fotografía paisajística de alta resolución",
                "Cálido de Sabana / Selva (30°C)"
        ));

        destinos.add(new Destino(
                6,
                "Medellín & Guatapé",
                "La Ciudad de la Eterna Primavera & El Peñol",
                "Antioquia - Valle de Aburrá",
                R.drawable.ic_medellin,
                "Reconocida mundialmente como referente de transformación urbana, innovación y resiliencia social. A pocas horas se encuentra el imponente monolito de la Piedra del Peñol y la arquitectura de zócalos coloridos de Guatapé.",
                "Enclavada en el valle interandino de Aburrá a 1.495 msnm. Su geografía quebrada está integrada mediante un sistema pionero de Metrocables que conectan las laderas con el corazón urbano.",
                "• Ascenso a los 740 escalones de la Piedra del Peñol y navegación en el embalse\n• Recorrido cultural por la Comuna 13 y el Museo de Antioquia / Plaza Botero\n• Visita al Jardín Botánico y Parque Arví a través del Metrocable\n• Ruta gastronómica en El Poblado y Laureles",
                "Primaveral Agradable (22°C - 26°C)"
        ));

        return destinos;
    }
}
