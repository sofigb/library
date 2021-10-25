// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#dataTable').DataTable();
});








  const request = async (url) => {
        const response = await fetch(url);
        const json = await JSON.stringify(response.json());
        return json;
    }
    let tree = request('humans.json');

    console.log(tree);
