import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class WorkSpace extends JPanel implements MouseListener, MouseMotionListener {
    ArrayList<City> cities = new ArrayList<City>();
    ArrayList<ArrayList<City>> routes= new ArrayList<ArrayList<City>>();
    ArrayList<ArrayList<City>> userconnect= new ArrayList<ArrayList<City>>();
    ArrayList<Route> routeList = new ArrayList<>();
    boolean flag1 = false;
    boolean flag2 = true;
    boolean b= false;
    int p;

    int preX, preY;
    boolean pressOut = false;
    City curCity;
    int curCityIndex;

    public WorkSpace() {
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    public void callCluster()
    {
        Cluster c = new Cluster();
        routes= c.clustering(cities);
        repaint();
    }

    public void userconnect()
    {
        flag1 = true;
    }

    public void TSPPro() throws InterruptedException {
        flag2 = true;
        TSPPro tspPro = new TSPPro("thread");
        tspPro.setCityList(cities);
        tspPro.run();
        routeList = tspPro.getRouteList();
        for (Route value : routeList) {
            System.out.println(value.getSrc().getLabel() + "----->" + value.getDest().getLabel());
        }
        System.out.println(tspPro.getShortestDistance());
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor (Color.red);
        for( City c: cities) {
            c.draw(g2);
        }
        for(int i =0;i< routes.size();i++)
        {
            int size = (routes.get(i)).size();
            for( int j=0 ; j< size ; j++)
            {
                routes.get(i).get(size-1).drawConnect(routes.get(i).get(j), g2);
            }
        }

        if(flag1 && b)
        {
            ArrayList<City> user = new ArrayList<City>();
            user.add(curCity);
            user.add(cities.get(p));
            userconnect.add(user);

            for(int i =0;i< userconnect.size();i++)
            {
                int j=0;
                userconnect.get(i).get(j).drawConnect(userconnect.get(i).get(j+1), g2);
            }
        }

        if (flag2) {
            for (Route route : routeList) {
                route.getSrc().drawConnect(route.getDest(), g2);
            }
        }
    }
    //	phoenix.drawConnect(tempe, g2);
    //tempe.draw(g2);
    //phoenix.draw(g2);


    public void mousePressed(MouseEvent e) {
        int curX = e.getX();
        int curY = e.getY();
        if(cities.isEmpty()) {
            String cityName = JOptionPane.showInputDialog("Enter City Name :");
            cities.add(new City(cityName, curX, curY, 10, 10));
            repaint();
        }
        else
        {
            boolean flag=false;
            for(City c: cities) {
                if (c.contains (e.getX(), e.getY())) {
                    //c.move(c.getX() + e.getX(), c.getY() + e.getY());
                    curCity=c;
                    curCityIndex = cities.indexOf(c);
                    //repaint();
                    pressOut = false;
                    flag=true;
                    break;
                }
                else {
                    pressOut = true;
                    continue;
                }
            }
            if(!flag)
            {
                String cityName = JOptionPane.showInputDialog("Enter City Name :");
                cities.add(new City(cityName, curX, curY, 10, 10));
                //repaint();
            }

        }
        if(flag1)
        {
            for (int k = 0 ;k < cities.size();k++)
            {
                if(cities.get(k).contains (e.getX(), e.getY()))
                {
                    curCity= cities.get(k);

                }
            }

        }
        if(pressOut) {
            repaint();
        }


    }
    public void mouseDragged(MouseEvent e) {
//		if (!pressOut) {
//			curCity.move(preX + e.getX(), preY + e.getY());
//			cities.add(new City(curCity.getLabel(), preX + e.getX(), preY + e.getY(), 10, 10));
//		}
    }
    public void mouseReleased(MouseEvent e) {
//		if (tempe.contains(e.getX(), e.getY())) {
//			tempe.move(preX + e.getX(), preY + e.getY());
        if(flag1)
        {
            if (!pressOut) {
                for (int k = 0 ;k < cities.size();k++)
                {

                    if(cities.get(k).contains (e.getX(), e.getY()))
                    {
                        p =k;
                    }
                }
            }
            b= true;

        }
        else
        {
            if (!pressOut) {
                curCity.move(e.getX(), e.getY());

                cities.remove(curCityIndex);
                cities.add(new City(curCity.getLabel(), e.getX(), e.getY(), 10, 10));

            }
        }
        repaint();
//		}
//		else {
        pressOut = false;
//		}
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }


    /**
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  <code>MOUSE_DRAGGED</code> events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * <code>MOUSE_DRAGGED</code> events may not be delivered during a native
     * Drag&amp;Drop operation.
     *
     * @param e
     */

}