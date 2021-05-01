using cop_mobile.ViewModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Syncfusion.SfPicker.XForms;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using cop_mobile.Model;
using cop_mobile.Services;
using System.Collections.ObjectModel;

namespace cop_mobile.View
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class DepartmentPage : ContentPage
    {
        private DepartmentViewModel vm;
        private MemberService memberService;

        public DepartmentPage(DepartmentViewModel viewModel)
        {
            memberService = DependencyService.Get<MemberService>();
            InitializeComponent();
            vm = viewModel;
            this.BindingContext = vm;
        }

        private void select_dept_Clicked(object sender, EventArgs e)
        {
            picker.IsOpen = true;
        }

        private async void picker_SelectionChanged(object sender, Syncfusion.SfPicker.XForms.SelectionChangedEventArgs e)
        {
            try
            {
                Department dept = (Department)picker.SelectedItem;
                vm.SelectedDept = dept;
                vm.Page = 1;
                CustomPage<Member> members = await memberService.getByDept(dept.deptId, 1);
                vm.TotalItems = members.TotalItems;
                vm.Members = new ObservableCollection<Member>(members.Content);
                picker.IsOpen = false;
            }
            catch (Exception ex)
            {
                await DisplayAlert("Error", ex.Message, "Ok");
            }
        }

        private async void listView_ItemTapped(object sender, Syncfusion.ListView.XForms.ItemTappedEventArgs e)
        {
            Member selectedMember = (Member)e.ItemData;
            MemberDetailViewModel vm = new MemberDetailViewModel(selectedMember);
            listView.SelectedItem = null;
            await Navigation.PushAsync(new MemberDetailPage(vm));
        }
    }
}