# HBV401G Tour Booking App

## Instructions

1. Once you unzip/git clone our repo, you should ensure that `sqlite-jdbc-3.7.2.jar` and `Tours.db`, under the `hallohallo` directory, should be under the same folder.
2. Go to hallohallo > src > main > src > java > test > hallohallo > YourLocation  
   Change this line:

```
String YourLocation = "/Users/owenwong/Desktop/HBV401G Software Development/HBV401G Final Project/hallohallo/";
```

Copy your absolute filepath of the `sqlite-jdbc-3.7.2.jar` file, which is under the `HBV401G Final Project > hallohallo` originally.
And paste it to

```
String YourLocation = "{Your Location, in absolute path}/"
```

Make sure you don't miss the "/" at the very last of `YourLocation`

3. Next, if you are using IntellJ IDEA, go to `File > Project Structure > Project Settings > Libraries > +` and add the file `sqlite-jdbc-3.7.2.jar` into the `hallohallo` directory

4. Enjoy the app!

---

### Link to presentation slides
<https://docs.google.com/presentation/d/1BYzwh7vC6fBx4wOl6yBbWDmPcODmF8ew/edit?usp=sharing&ouid=103930648485554260296&rtpof=true&sd=true>

### Link to UML Class Diagram
<https://drive.google.com/file/d/1_sC9HK7N4V0aQSzrbLMDRhdG13XJtU_e/view?usp=sharing>

### Link to UML Sequence Diagram
<https://drive.google.com/file/d/1K-ZIaNGMZfeIE-9fcYt5he4GJ2bNHX3h/view?usp=sharing>
