using cop_mobile.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace cop_mobile.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Calendar : ContentPage
    {
        private CalendarViewModel vm;

        public Calendar(CalendarViewModel calendarViewModel)
        {
            InitializeComponent();
            vm = calendarViewModel;
            calendar.BindingContext = vm;
            
        }
    }
}