package com.thirdnf.ResourceScheduler;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Date;

/**
 * A demo model, has its database hard coded.
 */
public class ScheduleModelDemo implements IScheduleModel
{
    private static final ICategory Green = new DemoCategory("Green", Color.green);
    private static final ICategory Blue  = new DemoCategory("Blue", Color.blue);

    private static final IResource Bobby = new DemoResource("Bobby");
    private static final IResource Johnny = new DemoResource("Johnny");
    private static final IResource Sally = new DemoResource("Sally");

    private static final IAppointment[] Appointments = new IAppointment[] {
            DemoAppointment.create("Appointment1", Green, Bobby, new Time(10, 0, 0), new Duration(0, 45, 0)),
            DemoAppointment.create("Appointment2", Blue, Johnny, new Time(13, 0, 0),  new Duration(1, 15, 0))
    };


    private static class DemoResource implements IResource
    {
        private final String _title;

        public DemoResource(@NotNull String title)
        {
            _title = title;
        }


        @Override
        public String getTitle()
        {
            return _title;
        }
    }


    private static class DemoCategory implements ICategory
    {
        private final Color _color;
        private final String _title;

        public DemoCategory(@NotNull String title, @NotNull Color color)
        {
            _title = title;
            _color = color;
        }


        @Override
        @NotNull
        public Color getColor()
        {
            return _color;
        }


        @NotNull
        @Override
        public String getTitle()
        {
            return _title;
        }
    }


    private static class DemoAppointment implements IAppointment
    {
        private final ICategory _catgegory;
        private final IResource _resource;
        private final String _title;
        private Time _time;
        private Duration _length;

        public DemoAppointment(@NotNull String title, ICategory category, IResource resource)
        {
            _title = title;
            _catgegory = category;
            _resource = resource;
        }


        @Override
        public ICategory getCategory()
        {
            return _catgegory;
        }

        @NotNull
        @Override
        public Time getTime()
        {
            return _time;
        }


        @Override
        public IResource getResource()
        {
            return _resource;
        }

        @NotNull
        @Override
        public Duration getDuration()
        {
            return _length;
        }

        @NotNull
        @Override
        public String getTitle()
        {
            return _title;
        }


        public void setTime(@NotNull Time time)
        {
            _time = time;
        }


        public void setLength(@NotNull Duration length)
        {
            _length = length;
        }


        public static DemoAppointment create(@NotNull String title, @NotNull ICategory category,
                                             @Nullable IResource resource,
                                             @NotNull Time time, @NotNull Duration duration)
        {
            DemoAppointment appointment = new DemoAppointment(title, category, resource);
            appointment.setTime(time);
            appointment.setLength(duration);

            return appointment;
        }
    }


    @Override
    public void visitAppointments(IAppointmentVisitor visitor, @NotNull Date dateTime)
    {
        // TODO - Actually break the appointments down by days.

        for (IAppointment appointment : Appointments) {
            visitor.visitAppointment(appointment);
        }
    }
}
